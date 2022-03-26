(ns app.hospital.ref.day-simulation-async
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

(defn async-arrive-at! [hospital person]
  (future (Thread/sleep (rand 5000))
          (dosync
            (println "Trying to add person:" person)
            (arrive-at! hospital person))))

(defn simulate-one-day-async []
  (let [hospital {:waiting-line (ref h.model/empty-queue)
                  :laboratory1  (ref h.model/empty-queue)
                  :laboratory2  (ref h.model/empty-queue)
                  :laboratory3  (ref h.model/empty-queue)}
        futures (mapv #(async-arrive-at! hospital %) (range 5))]

    (future
      (dotimes [_ 4]
        (Thread/sleep 2000)
        (pprint hospital)
        (pprint futures)))))

(simulate-one-day-async)