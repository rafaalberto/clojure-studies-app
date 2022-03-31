(ns app.hospital.schema.dynamic-map
  (:require [schema.core :as s])
  (:use [clojure pprint]))

(def PosInt (s/pred pos-int?))

(def Insurance [s/Keyword])

(def Patient
  {:id                         PosInt
   :name                       s/Str
   :insurances                 Insurance
   (s/optional-key :birthdate) s/Str})

(def Patients
  {PosInt Patient})

(def Visitors
  {PosInt [s/Str]})

(let [patient1 {:id 1 :name "Mary" :insurances [:x-ray]}]
  (pprint (s/validate Patients {1 patient1})))

(let [visitor1 {1 ["01/01/2022" "01/02/2022" "01/03/2022"]}]
  (pprint (s/validate Visitors visitor1)))