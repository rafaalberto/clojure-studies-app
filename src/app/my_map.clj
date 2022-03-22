(ns app.my-map)

(defn my-map [function sequence]
  (let [first-seq (first sequence)]
    (if (not (nil? first-seq))
      (do (function first-seq)
          (recur function (rest sequence))))))

(my-map println ["Rafael" true "Rosemeire" "José"])
(my-map println (range 100001))