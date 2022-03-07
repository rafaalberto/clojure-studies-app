(ns app.doseq)

(defn print-sequence []
  (doseq [number [0 1 2]]
    (println "Display number: " number)))

(print-sequence)

(defn multiple-sequences []
  (doseq [seq1 [3 6 9]
          seq2 [1 2 3]]
    (println seq1 " * " seq2 " = " (* seq1 seq2))))

(defn multiplication-table [number]
  (doseq [sequence (range 0 11)]
    (println number " * " sequence " = " (* number sequence))))

(println "Multiplication:")
(multiple-sequences)

(def multiplication-number 7)
(println "Multiplication table" multiplication-number)
(multiplication-table multiplication-number)