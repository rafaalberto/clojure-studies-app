(ns app.group-by)

(defn print-grades []
  (let [{approved true
         reproved false} (group-by #(>= % 5) [3 10 4.5 5 10 9 8 0])]
    (println "approved: " approved)
    (println "reproved: " reproved)))

(print-grades)