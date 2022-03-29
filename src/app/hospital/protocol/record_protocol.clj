(ns app.hospital.protocol.record-protocol)

(defprotocol Chargeable
  (should-pre-authorized? [patient service price]))

(defrecord ParticularPatient [id name birthdate])

(extend-type ParticularPatient
  Chargeable
  (should-pre-authorized? [_ _ price]
    (> price 50)))

(defrecord InsurancePatient [id name birthdate insurance]
  Chargeable
  (should-pre-authorized? [patient service _]
    (let [insurance (:insurance patient)]
      (not (some #(= % service) insurance)))))

(let [particular-patient (->ParticularPatient 1 "John" "20/07/1981")
      insurance-patient (->InsurancePatient 2 "Tom" "18/10/1987" [:x-ray :blood-test])]

  (println "Particular should pre-authorized?" (should-pre-authorized? particular-patient :x-ray 55))
  (println "Insurance should pre-authorized?" (should-pre-authorized? insurance-patient :x-ray 200)))