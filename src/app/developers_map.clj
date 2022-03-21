(ns app.developers_map)

(def developers [{:name         "Rafael"
                  :certificates ["Clojure" "Java" "Kotlin" "Node"]}
                 {:name         "Mary"
                  :certificates ["Python" "Java" "React"]}
                 {:name         "Jim"
                  :certificates ["PHP" ".Net" "Kotlin"]}])

(println "Total certificates: " (->> developers
              (map :certificates)
              (map count)
              (reduce +)))