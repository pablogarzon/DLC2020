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
        	<article class="box" v-for="doc in docs" :key="doc.iddoc">
                <div class="columns is-vcentered">
                    <div class="column is-half">
                        <h4 class="title is-4">{{doc.nombre}}</h3>
                    </div>
                    <div class="column is-half has-text-right">
                        <a href="#" @click="getFile(doc)">Descargar</a>
                    </div>
                </div>
            </article>
        	<h4 v-if="isResultEmpty" class="title is-4">No se encontraron resultados.</h4>
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
		    	docs: [],
		    	isResultEmpty: false
		    };
		  },
		  methods: {
		  	submit: function (e) {
		  		this.isLoading = true;
		  		this.isResultEmpty = false;
	  		    axios
                  .get('query?q=' + this.query)
      			  .then(response => {
      			  	this.docs = response.data;
      			  	this.isLoading = false;
      			  	if(response.data.length === 0)
      			  		this.isResultEmpty = true;
      			  });
		  		e.preventDefault();
		  	},
		  	getFile: function (doc) {
		  		axios
                  .post('document', doc)
      			  .then(response => {
      			  	const url = window.URL.createObjectURL(new Blob([response.data]));
      			  	const link = document.createElement('a');
					link.href = url;
					link.setAttribute('download', doc.nombre);
					document.body.appendChild(link);
					link.click();
      			  });
		  	}
		  }
		});
    </script>
  </body>
</html>
