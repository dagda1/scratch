(ns scratch.core
  (require [clojure.string :as str :only (split-lines join)]))

(defn is-prime? [n]
  (if (= n 2)
    true
    (if (even? n) false
       (let [root (Math/floor (int (Math/sqrt n)))]
         (loop [i 3]
           (if (> i root) true
               (if (= 0 (mod n i)) false
                   (recur (+ i 2)))))))))

(defn n-primes [n]
  (loop [curr 3 acc [2]]
    (if (= (count acc) n)
      acc
      (recur (+ 2 curr) (if (is-prime? curr)
                          (conj acc curr)
                          acc)))))

(defn nth-prime [n]
  (last (n-primes n)))

(defn print-primes [[x & xs]]
  (prn x)
  (if (seq xs)
    (recur xs)))

(let [input "1\n10001"
      ranks (rest (map read-string (str/split-lines input)))
      primes (map nth-prime ranks)]
  (time (print-primes  primes)))  ; Elapsed time: 211.82
