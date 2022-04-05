(ns app.generative-test
  (:require [clojure.test :refer :all]
            [app.hospital.generative-tests.logic :refer :all]
            [app.hospital.generative-tests.model :as h.model]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.test.check.properties :as prop]
            [schema-generators.generators :as sgen]
            [schema.core :as s]
            [app.hospital.generative-tests.model :as h.model]))

(s/set-fn-validation! true)

(deftest waiting-line-vacancy?-test

  (testing "When waiting line is empty"
    (is (waiting-line-vacancy? {:waiting-line []} :waiting-line)))

  (testing "When there is vacancy"
    (is (waiting-line-vacancy? {:waiting-line [1 3 8 10]} :waiting-line)))

  (testing "When there is no vacancy"
    (is (not (waiting-line-vacancy? {:waiting-line [1 2 3 4 5]} :waiting-line)))))

(deftest arrive-to-hospital-test

  (testing "Arrive to waiting line"
    (is (= {:waiting-line [1 3 2]}
           (arrive {:waiting-line [1 3]} :waiting-line 2)))))

;property-based testing

(defn convert-vector-to-queue [vector]
  (reduce conj h.model/empty-queue vector))

(def random-name-gen (gen/fmap clojure.string/join
                               (gen/vector gen/char-alphanumeric 5 10)))

(def queue-not-full-gen (gen/fmap convert-vector-to-queue
                                  (gen/vector random-name-gen 0 4)))

(defn transfer-test [hospital to]
  (try
    (transfer hospital :waiting-line to)
    (catch IllegalStateException _
      hospital)))

(defspec transfer-keep-patients-quantity 50
         (prop/for-all
           [waiting-line (gen/fmap convert-vector-to-queue (gen/vector random-name-gen 0 50))
            x-ray queue-not-full-gen
            laboratory1 queue-not-full-gen
            transfer-to (gen/vector (gen/elements [:x-ray :laboratory1]) 10 50)]
           (let [hospital-initial {:waiting-line waiting-line :x-ray x-ray :laboratory1 laboratory1}
                 hospital-final (reduce transfer-test hospital-initial transfer-to)]
             (= (total-of-patients hospital-initial)
                (total-of-patients hospital-final)))))

(defn add-waiting-line [[hospital queue]]
  (assoc hospital :waiting-line queue))

(def hospital-gen
  (gen/fmap add-waiting-line
            (gen/tuple (gen/not-empty (sgen/generator h.model/Hospital))
                       queue-not-full-gen)))

(def arrive-gen (gen/tuple (gen/return arrive)
                           (gen/return :waiting-line)
                           random-name-gen
                           (gen/return 1)))

(defn add-not-existent [department]
  (keyword (str department "-non-existent")))

(defn transfer-gen [hospital]
  (let [departments (keys hospital)
        departments-non-existent (map add-not-existent departments)
        all-departments (concat departments departments-non-existent)]
    (gen/tuple (gen/return transfer)
               (gen/elements all-departments)
               (gen/elements all-departments)
               (gen/return 0))))

(defn action-gen [hospital]
  (gen/one-of [arrive-gen
               (transfer-gen hospital)]))

(defn actions-gen [hospital]
  (gen/not-empty (gen/vector (action-gen hospital) 1 100)))

(defn execute-action [situation [function param1 param2 difference-success]]
  (let [hospital (:hospital situation)
        difference-current (:difference situation)]
    (try
      (let [hospital-new (function hospital param1 param2)]
        {:hospital   hospital-new
         :difference (+ difference-success difference-current)})
      (catch IllegalStateException _
        situation)
      (catch AssertionError _
        situation))))

(defspec simulate-one-day 50
         (prop/for-all
           [hospital-initial hospital-gen]
           (let [action (gen/generate (actions-gen hospital-initial))
                 situation-initial {:hospital hospital-initial :difference 0}
                 total-of-patients-initial (total-of-patients hospital-initial)
                 situation-final (reduce execute-action situation-initial action)
                 total-of-patients-final (total-of-patients (:hospital situation-final))]
             (println total-of-patients-final total-of-patients-initial (:difference situation-final))
             (is (= (- total-of-patients-final (:difference situation-final))
                    total-of-patients-initial)))))