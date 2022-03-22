(ns app.my-loop)

(defn count-loop [elements]
  (println "Code before loop")
  (loop [total 0
         elements-left elements]
    (if (seq elements-left)
      (recur (inc total) (next elements-left))
      total)))

(println "Total:" (count-loop ["Rafael" "Rosemeire" "José"]))