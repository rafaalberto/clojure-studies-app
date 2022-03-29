(ns app.hospital.defmulti.defmulti-class
  (:use [clojure pprint]))

(defrecord ParticularPatient [id name])
(defrecord InsurancePatient [id name])

(defmulti should-pre-authorize? class)

(defmethod should-pre-authorize? ParticularPatient [patient]
  (println "Calling Particular Patient:" patient)
  true)

(defmethod should-pre-authorize? InsurancePatient [patient]
  (println "Calling Insurance Patient:" patient)
  true)

(let [particular (->ParticularPatient 1 "John")
      insurance (->InsurancePatient 2 "Mary")]
  (pprint (should-pre-authorize? particular))
  (pprint (should-pre-authorize? insurance)))