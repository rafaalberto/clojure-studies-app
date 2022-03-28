(ns app.hospital.record.patient-record
  (:use [clojure pprint]))

(defrecord Patient [id name birthdate])

;option 1
(pprint (->Patient 1 "John" "01/01/1998"))

;option 2
(pprint (Patient. 1 "John" "01/01/1998"))

;option 3
(pprint (map->Patient {:id 1 :name "John" :birthdate "01/01/1998"}))