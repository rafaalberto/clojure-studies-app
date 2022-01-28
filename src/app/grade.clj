(ns app.grade)

(defn result [grade1 grade2]
  (let [average (/ (+ grade1 grade2) 2)]
    (str "The final grade is: "
         (cond
           (< average 5) "Reproved"
           (and (>= average 5) (< average 7)) "Exam"
           :else "Approved"))))

(println (result 6 6))