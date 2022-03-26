(ns app.hospital.atom.day-simulation
  (:require [app.hospital.atom.model :as h.model]
            [app.hospital.atom.logic :as h.logic])
  (:use [clojure pprint]))

(defn- set-arrival! [hospital person]
  (swap! hospital h.logic/arrive-at :waiting-line person))

(defn- start-arrival [hospital person]
  (.start (Thread. (fn [] (set-arrival! hospital person)))))

(defn- transfer! [hospital from to]
  (swap! hospital h.logic/transfer from to))

(defn simulate-day []
  (let [hospital (atom (h.model/new-hospital))]
    ;(dotimes [person 5]
    ;  (start-arrival hospital person))
    (set-arrival! hospital "John")
    (set-arrival! hospital "Mary")
    (set-arrival! hospital "Rose")
    (set-arrival! hospital "Charles")
    (pprint hospital)
    (transfer! hospital :waiting-line :laboratory1)
    (transfer! hospital :waiting-line :laboratory2)
    (transfer! hospital :waiting-line :laboratory3)
    (transfer! hospital :laboratory2 :laboratory3)
    (.start (Thread. (fn [] (Thread/sleep 4000)
                       (pprint hospital))))))

(simulate-day)