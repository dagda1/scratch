(ns scratch.flatten)

(def lst '{a {p 1, q 2}
           b {m 3, n 4}})

((fn [xs]
   (into {} (for [[x y] xs [a b] y]
              [[x a] b])))lst)

; '{[a p] 1, [a q] 2 [b m] 3, [b n] 4}
;; 
;
