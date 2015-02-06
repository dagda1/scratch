(ns scratch.core
  (require [clojure.string :as str :only (split-lines join)]))

(defn is-prime? [n]
  (empty? (filter #(= 0 (mod n  %)) (range 2 n))))

(defn nth-prime [n]
  (last (take n (filter #(is-prime? %) (iterate inc 2)))))

(let [input "2\n3\n6"
      ranks (rest (map read-string (str/split-lines input)))
      primes (map nth-prime ranks)]
  (map prn primes))
;(work (slurp *in*))
