(ns app.laziness)

(def countries
  [{:name "Brazil" :continent "South America"}
   {:name "USA" :continent "North America"}
   {:name "France" :continent "Europe"}
   {:name "Angola" :continent "Africa"}])

(defn is-from-europe? [countries]
  (prn "calling countries")
  (= (:continent countries) "Europe"))

(def europeans (filter is-from-europe? countries))

(println europeans)