(ns app.group-by)

(defn grades [student-grades]
  (let [{approved true
         reproved false} (group-by #(>= (:grade %) 5) student-grades)]
    {:approved (seq approved)
     :reproved (seq reproved)}))