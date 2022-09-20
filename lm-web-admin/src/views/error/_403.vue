<template>
  <div class="Forbidden_box">
    <div>
      <span
        class="
          bg-clip-text
          text-transparent
          bg-gradient-to-r
          from-green-400
          to-blue-500
        "
      >
      Forbidden
      </span>
    </div>

    <div class="box2">
      <span
        class="
          bg-clip-text
          text-transparent
          bg-gradient-to-r
          from-green-400
          to-blue-500
        "
        >不能访问哦！</span
      >
    </div>
  </div>
</template>
<style >
.Forbidden_box {
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 5rem;
  flex-flow: column;
}
.box2 {
  font-size: 1rem;
  font-weight: 1000;
}
</style>