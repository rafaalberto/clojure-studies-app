(ns app.hospital.day-simulation
  (:require [app.hospital.model :as h.model]
            [app.hospital.logic :as h.logic])
  (:use [clojure pprint]))

(defn- set-arrival! [hospital person]
  (swap! hospital h.logic/arrive-at :waiting-line person))

(defn- start-arrival [hospital person]
  (.start (Thread. (fn [] (set-arrival! hospital person)))))

(defn simulate-day []
  (let [hospital (atom (h.model/new-hospital))]
    (dotimes [person 6]
      (start-arrival hospital person))
    (.start (Thread. (fn [] (Thread/sleep 4000)
                       (pprint hospital))))))

(simulate-day)