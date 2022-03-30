(ns app.hospital.schema.constrained
  (:require [schema.core :as s])
  (:use [clojure pprint]))

(def Patient
  "Patient schema"
  {:id   (s/constrained s/Int pos?)
   :name s/Str})

(pprint (s/validate Patient {:id 1 :name "Joana"}))