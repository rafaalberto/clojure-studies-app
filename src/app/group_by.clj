(ns app.group-by)

(defn print-grades []
  (let [grades '({:name "Rafael" :grade 10}
                 {:name "Jim" :grade 4.5}
                 {:name "Mary" :grade 9.5}
                 {:name "John" :grade 3}
                 {:name "Rose" :grade 8})
        {approved true
         reproved false} (group-by #(>= (:grade %) 5) grades)]
    {:approved (seq approved)
     :reproved (seq reproved)}))

(print-grades)