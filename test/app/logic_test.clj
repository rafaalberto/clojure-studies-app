(ns app.logic-test
  (:require [clojure.test :refer :all]
            [app.hospital.tests.logic :refer :all]
            [app.hospital.tests.model :as h.model]
            [schema.core :as s])
  (:import (clojure.lang ExceptionInfo)))

(s/set-fn-validation! true)

;boundary testing
(deftest waiting-line-vacancy?-test
  (testing "When waiting line is empty"
    (is (waiting-line-vacancy? {:waiting-line []} :waiting-line)))

  (testing "When waiting line has few people"
    (is (waiting-line-vacancy? {:waiting-line [10 15]} :waiting-line)))

  (testing "When waiting line is almost full"
    (is (waiting-line-vacancy? {:waiting-line [10 15 18 20]} :waiting-line)))

  (testing "When waiting line is full"
    (is (not (waiting-line-vacancy? {:waiting-line [10 15 18 20 3]} :waiting-line))))

  (testing "When waiting line has more patient than its capacity"
    (is (not (waiting-line-vacancy? {:waiting-line [10 15 18 20 3 12]} :waiting-line))))

  (testing "When there is no department"
    (is (not (waiting-line-vacancy? {:waiting-line [10 15 18 20]} :laboratory1)))))

(deftest arrive-test
  (let [hospital-full {:waiting-line [8 2 7 10 50]}]
    (testing "Accept patient while there is vacancy at waiting line"
      (is (= {:waiting-line [10 17 23]}
             (arrive {:waiting-line [10 17]} :waiting-line 23)))

      (is (= {:waiting-line [10 17 23 4 15]}
             (arrive {:waiting-line [10 17 23 4]} :waiting-line 15))))

    (testing "Not accept when there is no vacancy at waiting line"

      (is (thrown? ExceptionInfo
                   (arrive hospital-full :waiting-line 20))))))

(deftest transfer-test
  (testing "Accept new patient if there is vacancy"

    (let [hospital {:waiting-line (conj h.model/empty-queue "7")
                    :x-ray        h.model/empty-queue}]
      (is (= {:waiting-line []
              :x-ray        ["7"]}
             (transfer hospital :waiting-line :x-ray))))

    (let [hospital {:waiting-line (conj h.model/empty-queue "10" "17")
                    :x-ray        (conj h.model/empty-queue "12")}]
      (is (= {:waiting-line ["17"]
              :x-ray        ["12" "10"]}
             (transfer hospital :waiting-line :x-ray)))))

  (testing "Reject new patient if there is no vacancy"
    (let [hospital-full {:waiting-line (conj h.model/empty-queue 5)
                         :x-ray        (conj h.model/empty-queue 1 8 10 12 15)}]
      (is (thrown? ExceptionInfo
                   (transfer hospital-full :waiting-line :x-ray)))))

  (testing "No transfer without hospital"
    (is (thrown? ExceptionInfo (transfer nil :waiting-line :x-ray))))

  (testing "Mandatory conditions"
    (let [hospital {:waiting-line (conj h.model/empty-queue "5")
                    :x-ray        (conj h.model/empty-queue "1" "2" "3")}]
      (is (thrown? AssertionError
                   (transfer hospital :not-exists :x-ray)))
      (is (thrown? AssertionError
                   (transfer hospital :waiting-line :not-exists))))))
