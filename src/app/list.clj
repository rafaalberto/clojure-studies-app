(ns app.list)

(defn display [number]
  (println "number: " number))

(map display (range 1 11))