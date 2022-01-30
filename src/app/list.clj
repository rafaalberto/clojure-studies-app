(ns app.list)

(defn display-list [number]
  (println "Display number: " number))

(map display-list (range 1 11))