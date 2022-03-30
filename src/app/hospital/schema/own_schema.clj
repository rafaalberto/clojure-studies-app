(ns app.hospital.schema.own-schema
  (:require [schema.core :as s])
  (:use [clojure pprint]))

(s/set-fn-validation! true)

(def Patient
  "Patient schema"
  {:id   s/Num
   :name s/Str})

(pprint (s/explain Patient))
(pprint (s/validate Patient {:id 1 :name "John"}))

(s/defn new-patient :- Patient
  [id :- s/Num
   name :- s/Str]
  {:id id :name name})

(pprint (new-patient 1 "Rafa"))