(ns app.hospital.ref.day-simulation
  (:use [clojure pprint])
  (:require [app.hospital.atom.model :as h.model]))

(defn line-available? [line]
  (-> line
      count
      (< 5)))

(defn arrive-at [line person]
  (if (line-available? line)
    (conj line person)
    (throw (ex-info "Line is already full" {:trying-add person}))))

(defn arrive-at! [hospital person]
  (let [line (get hospital :waiting-line)]
    (alter line arrive-at person)))

(defn simulate-one-day []
  (let [hospital {:waiting-line (ref h.model/empty-queue)
                  :laboratory1  (ref h.model/empty-queue)
                  :laboratory2  (ref h.model/empty-queue)
                  :laboratory3  (ref h.model/empty-queue)}]
    (dosync
      (arrive-at! hospital "John")
      (arrive-at! hospital "Mary")
      (arrive-at! hospital "Tom")
      (arrive-at! hospital "Rose")
      (arrive-at! hospital "Joseph"))
    (pprint hospital)))

(simulate-one-day)