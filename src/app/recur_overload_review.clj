(ns app.recur-overload-review)

(def transactions
  [{:value 500}
   {:value 200}
   {:value 300}
   {:value 400}
   {:value 500}])

(defn sum-transactions
  ([transactions]
   (sum-transactions 0 transactions))
  ([total transactions]
   (if-let [current-transaction (first transactions)]
     (recur (+ (:value current-transaction) total) (rest transactions))
     total)))

;option 1
(println "Amount: " (sum-transactions transactions))

;option 2
(println "Amount: "
         (reduce + (map #(:value %) transactions)))