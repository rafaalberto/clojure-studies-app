(ns app.my-map)

(defn my-map [function sequence]
  (let [first-seq (first sequence)]
    (if (not (nil? first-seq))
      (do (function first-seq)
          (my-map function (rest sequence))))))

(my-map println ["Rafael" true "Rosemeire" "José"])