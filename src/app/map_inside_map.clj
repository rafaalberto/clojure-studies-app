(ns app.map-inside-map)

(def databases {:mongodb {:url "http://mongo"}})

(println "URL:" (:url (:mongodb databases)))

;other way to get key inside map
(println "URL:" (get-in databases [:mongodb :url]))