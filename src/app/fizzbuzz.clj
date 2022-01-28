(ns app.fizzbuzz)

;;fizz divided-by-3
;;buzz divided-by-5
;;fizzbuzz divided-by-3 and divided-by-5
;;default number

(defn divided-by? [dividend divisor]
  (= 0 (mod dividend divisor)))

(defn fizzbuzz [number]
  (println "Result: "
           (cond
             (and (divided-by? number 3) (divided-by? number 5)) "fizzbuzz"
             (divided-by? number 3) "fizz"
             (divided-by? number 5) "buzz"
             :else number)))

(fizzbuzz 15)