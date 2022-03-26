(ns app.hospital.atom.model
  (:import (clojure.lang PersistentQueue)))

(def empty-queue PersistentQueue/EMPTY)

(defn new-hospital []
  {:waiting-line empty-queue
   :laboratory1  empty-queue
   :laboratory2  empty-queue
   :laboratory3  empty-queue})