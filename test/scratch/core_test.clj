(ns scratch.core-test
  (:require [clojure.test :refer :all]
            [scratch.core :refer :all]
            [clojure.string :as str :only (split split-lines)]))

(def input "1\n4\n3\n7 4\n2 4 6\n8 5 9 3")

(deftest a-test
  (let [sample (map (fn [s]
                         (vec (map read-string (str/split s #" ")))
                         ) (str/split-lines input))
        triangle (take-last (- (count sample) 2) sample)
        ]
    (prn triangle)
    (testing "FIXME, I fail."
      (is (= 4 (count triangle))))))
