(ns elite-rpg.sprites
  (:require
    [elite-rpg.resources :as r]))

(def a:sprites (atom {}))

(defn add-sprite [app sprite-id res-id x y width height]
  (let [sprite (js/PIXI.Sprite. (r/get-texture res-id))]
    (set! (.-position.x sprite) x)
    (set! (.-position.y sprite) y)
    (set! (.-scale sprite) true)
    (set! (.-width sprite) width)
    (set! (.-height sprite) height)
    ; (set! (.-anchor.x sprite) 0.5)
    ; (set! (.-anchor.y sprite) 0.5)
    ; (set! (.-rotation sprite) rotation)
    (.addChild (.-stage app) sprite)
    (swap! a:sprites assoc sprite-id sprite)
    sprite))

(defn get-sprite [sprite-id]
  (get @a:sprites sprite-id))
