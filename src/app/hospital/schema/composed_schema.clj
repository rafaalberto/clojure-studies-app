(ns app.hospital.schema.composed-schema
  (:require [schema.core :as s])
  (:use [clojure pprint]))

(s/set-fn-validation! true)

(defn higher-or-equal-zero? [number]
  (>= number 0))

(def PosInt (s/pred pos-int?))

(def CurrencyFormat (s/constrained s/Num higher-or-equal-zero?))

(def Patient
  {:id   PosInt
   :name s/Str})

(def Order
  {:patient Patient
   :service s/Keyword
   :amount  CurrencyFormat})

(s/defn add-patient :- Patient
  [id :- PosInt
   name :- s/Str]
  {:id id :name name})

(s/defn add-order :- Order
  [patient :- Patient
   service :- s/Keyword
   amount :- CurrencyFormat]
  {:patient patient :service service :amount amount})

(let [patient (add-patient 1 "Joana")
      order (add-order patient :x-ray 100.00)]
  (pprint order))