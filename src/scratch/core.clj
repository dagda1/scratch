(ns scratch.core)

(def s #{1 :e})

((fn [coll]
   (letfn [(f [curr x acc]
             (let [n (conj acc [curr])
                   e (concat n (map #(conj % curr) acc))]
               (if-not (first x)
                 e
                 (recur (first x) (rest x) e))))]
     (if (empty? coll)
       #{}
       (let [result (set (f (first coll) (sort (rest coll)) []))]
         (set (map set (conj result []))))))) s)

;; (1)
;; (2 3 4)
;; [
;;  []
;;  [1]
;;  [2]
;;  [1 2]
;;  [3]
;;  [1 3]
;;  [2 3]
;;  [1 2 3]
;;  [4]
;;  [1 4]
;;  [2 4]
;;  [1 2 4]
;;  [3 4]
;;  [1 3 4]
;;  [2 3 4]
;;  [1 2 3 4]
;;  ]
