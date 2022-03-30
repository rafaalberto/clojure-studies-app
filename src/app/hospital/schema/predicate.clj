(ns app.hospital.schema.predicate
  (:require [schema.core :as s])
  (:use [clojure pprint]))

(defn positive? [number]
  (> number 0))

(def PositiveNumber (s/pred positive?))

(pprint (s/validate PositiveNumber 1))