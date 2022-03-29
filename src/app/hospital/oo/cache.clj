(ns app.hospital.oo.cache
  (:require [app.hospital.oo.logic :as h.logic])
  (:use [clojure pprint]))

(defn- load-if-not-exists [cache id loader]
  (if (contains? cache id)
    cache
    (let [register (loader id)]
      (assoc cache id register))))

;option 1 - functional

(defn load-patient [id]
  (println "Loading:" id)
  (Thread/sleep 1000)
  {:id id :loaded-at (h.logic/now)})

(pprint (load-if-not-exists {} 2 load-patient))
(pprint (load-if-not-exists {10 {:id 10}} 10 load-patient))

;option 2 - object-oriented

(defprotocol Loadable
  (load! [this id]))

(defrecord Cache
  [cache loader]
  Loadable
  (load! [_ id]
    (swap! cache load-if-not-exists id loader)
    (get @cache id)))

(let [patients (->Cache (atom {}) load-patient)]
  (pprint patients)
  (load! patients 15)
  (load! patients 30)
  (load! patients 15)
  (pprint patients))