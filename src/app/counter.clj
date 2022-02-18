(ns app.counter)

(defn sum
  ([number]
   (sum 0 number))
  ([total number]
   (if (< number 1)
     total
     (recur (+ total number) (- number 1)))))

;recur to avoid StackOverflowError
(println "Result: " (sum 100000))