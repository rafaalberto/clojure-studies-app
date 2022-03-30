(ns app.hospital.schema.validate-arguments
  (:require [schema.core :as s])
  (:use [clojure pprint]))

(s/set-fn-validation! true)

(s/defn new-patient
  [id :- Long
   name :- s/Str]
  {:id id :name name})

(pprint (new-patient 1 "John"))
(pprint (new-patient "2" "Mary"))