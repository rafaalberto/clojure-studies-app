(ns app.map-inside-map)

(def databases {:mongodb {:url "http://mongo"}})

(println "URL:" (:url (:mongodb databases)))

;other way to get key inside map
(println "URL:" (get-in databases [:mongodb :url]))

;other way to display using thread-first
(println "URL thread-first: " (-> databases
                                  :mongodb
                                  :url))

(println "URL updated:"
         (update-in databases [:mongodb :url]
                    #(if (= % "http://mongo") "https://mongodb" %)))

(def developers {:1 {:name      "Rafael"
                     :languages ["Clojure" "Java" "Kotlin" "Javascript"]}})

(println "Developers:" developers)
(println "Languages count: " (-> developers :1 :languages count))