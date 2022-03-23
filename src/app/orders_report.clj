(ns app.orders-report
  (:require [app.orders-database :as a.database]
            [app.orders_logic :as a.logic]))

(defn high-expense? [report]
  (str "user: " (:user-id report)
       " => " (>= (:total-amount report) 5000)))

(defn report-by-user-sorted [orders]
  (->> orders
       a.logic/report-by-user
       (sort-by :total-amount)
       reverse))

(let [orders (a.database/all-orders)
      report (report-by-user-sorted orders)]
  (println "Report sorted:" report)
  (println "Top 2:" (take 2 report))
  (println "Above 500:" (filter #(> (:total-amount %) 500) report))
  (println "Is anyone above 500?" (some #(> (:total-amount %) 500) report))
  (println "High expenses:" (keep high-expense? report)))