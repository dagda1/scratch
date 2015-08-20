(ns scratch.core
  (require [clojure.string :as str :only (split-lines join split)]))

(defn numberify [str]
  (vec (map read-string (str/split str #" "))))

(defrecord TreeNode [val left right])

(defn preprocess-input [n xs]
  (let [source (map vector (range 1 n) xs)]
    (->> source
         (map (fn [[k v]]
                {k v}))
         (into {}))))

(defn build-tree [val source]
  (when-let [[l r] (get source val)]
    (TreeNode. val (build-tree l source) (build-tree r source))))

(let [input "11\n2 3\n4 -1\n5 -1\n6 -1\n7 8\n-1 9\n-1 -1\n10 11\n-1 -1\n-1 -1\n-1 -1"
      lines (str/split-lines input)
      tl (read-string (first lines))
      tree-lines (map numberify (drop 1 (take (inc tl) lines)))
      tree-source (preprocess-input tl tree-lines)]
  (prn (build-tree 1 tree-source)))
