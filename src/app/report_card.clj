(ns app.report-card)

(def report
  {:name "Rafael" :grades [10 6 5]})

(defn average [report]
  (let [grades (:grades report) total (reduce + grades)]
    (double (/ total (count grades)))))

(println "Average: " (average report))