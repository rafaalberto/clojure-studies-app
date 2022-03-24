(ns app.queue
  (:import (clojure.lang PersistentQueue))
  (:use [clojure pprint]))

(defn display-queue []
  (let [waiting-line (conj PersistentQueue/EMPTY "Rafa" "Mary" "John")]
    (println "Queue")
    (println (seq waiting-line))
    (println (seq (conj waiting-line "Jim")))
    (println (seq (pop waiting-line)))
    (pprint waiting-line)))

(display-queue)

