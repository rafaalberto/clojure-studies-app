(ns app.my-reduce)

(defn count-elements
  ([elements]
   (count-elements 0 elements))
  ([total elements]
   (prn total elements)
   (if (seq elements)
     (recur (inc total) (rest elements))
     total)))

(println "Total:" (count-elements ["Rafael" "Rosemeire" "José"]))