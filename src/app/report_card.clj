(ns app.report-card)

(def grades-report {:grades [10 8 6]})

(defn average [grades-report]
  (let [{grades :grades} grades-report
        total (reduce + grades)]
    (double (/ total (count grades)))))

(defn print-report [grades-report]
  (let [average-result (average grades-report)]
    (str "Average: " average-result " - "
         "Result: "
         (cond (>= average-result 7) "Approved"
               (and (>= average-result 5) (< average-result 7)) "Exam"
               :else "Rejected"))))

(println (print-report grades-report))