<!DOCTYPE html>
<html>
  <head>
  	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Buscador</title>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.8.2/css/bulma.min.css"
    />
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.19.2/axios.min.js"></script>
  </head>
  <body>
    <section class="section" id="app">
      <div class="container">
        <header style="section">
          <h1 class="title">Buscador</h1>
          <form action="" method="GET" @submit="submit">
            <div class="field has-addons">
              <div class="control is-expanded" v-bind:class="{ 'is-loading': isLoading }">
                <input
                  class="input is-rounded"
                  type="text"
                  v-model="query"
                  placeholder="Termino de b&uacute;squeda"
                />
              </div>
              <div class="control">
                <input
                  type="submit"
                  class="button is-info is-rounded"
                  value="Search"
                />
              </div>
            </div>
          </form>
        </header>
        <section class="section">
            <article class="box" v-for="doc in docs">
                <div class="columns is-vcentered">
                    <div class="column is-half">
                        <h4 class="title is-4">{{doc.nombre}}</h3>
                    </div>
                    <div class="column is-half has-text-right">
                        <a href="#">Descargar</a>
                    </div>
                </div>
            </article>
        </section>
      </div>
    </section>
    <script>
    	var app = new Vue({
		  el: '#app',
		  data () {
		    return {
		    	query: '',
		    	isLoading: false,
		    	results: []
		    };
		  },
		  methods: {
		  	submit: function (e) {
		  		this.isLoading = true;
	  		    axios
                  .get('dlc2020/query=' + this.query)
      			  .then(response => {
      			  	this.docs = response.data;
      			  	this.isLoading = false;
      			  });
		  		e.preventDefault();
		  	}
		  }
		});
    </script>
  </body>
</html>
