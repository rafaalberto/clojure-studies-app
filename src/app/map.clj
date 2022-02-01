(ns app.map)

(def transactions
  [{:value 30.0, :type "Expense", :date "2022-02-01"}
   {:value 300.0, :type "Savings", :date "2022-01-19"}
   {:value 50.0, :type "Expense", :date "2022-01-10"}])

(defn report [transactions]
  (select-keys transactions [:value :date]))

(defn expense? [transactions]
  (= (:type transactions) "Expense"))

(println "Report: " (map report transactions))
(println "Expenses: " (filter expense? transactions))