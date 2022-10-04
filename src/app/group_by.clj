(ns app.group-by)

(defn print-grades []
  (let [grades [3 10 4.5 5 10 9 8 0]
        {approved true
         reproved false} (group-by #(>= % 5) grades)]
    {:approved approved
     :reproved reproved}))

(print-grades)