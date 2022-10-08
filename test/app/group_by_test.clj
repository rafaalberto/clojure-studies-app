(ns app.group-by-test
  (:require [clojure.test :refer :all])
  (:require [app.group-by :refer [grades]]))

(deftest group-by-test
  (testing "grouping students grades by status"
    (let [student-grades '({:name "Jay" :grade 7}
                           {:name "Jim" :grade 4.5}
                           {:name "Mary" :grade 3.5}
                           {:name "John" :grade 6}
                           {:name "Rose" :grade 8})]
      (is (= {:approved '({:name "Jay", :grade 7}
                          {:name "John", :grade 6}
                          {:name "Rose", :grade 8}),
              :reproved '({:name "Jim", :grade 4.5}
                          {:name "Mary", :grade 3.5})}
             (grades student-grades))))))
