(ns app.hospital.defmulti.defmulti-function
  (:use [clojure pprint]))

(defrecord ParticularPatient [id name status])
(defrecord InsurancePatient [id name status insurance])

(defn authorization-type [order]
  (let [patient (:patient order)
        status (:status patient)
        emergency? (= :emergency status)]
    (if emergency?
      :authorized
      (class patient))))

(defmulti should-pre-authorize? authorization-type)

(defmethod should-pre-authorize?
  :authorized [_] false)

(defmethod should-pre-authorize? ParticularPatient [order]
  (>= (:value order 0) 50))

(defmethod should-pre-authorize? InsurancePatient [order]
  (not (some #(= % (:service order)) (:insurance (:patient order)))))

(let [particular (->ParticularPatient 1 "John" :ok)
      insurance (->InsurancePatient 2 "Mary" :emergency [:x-ray])]
  (pprint (should-pre-authorize? {:patient particular :value 50 :service :x-ray}))
  (pprint (should-pre-authorize? {:patient insurance :value 100 :service :x-ray})))