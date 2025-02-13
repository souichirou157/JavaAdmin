import React from 'react';
import ReactDOMServer from 'react-dom/server';



const App = () => {
    return (
      <option value="">
      
      </option>
    );
  };
  

  const pageString = ReactDOMServer.renderToString(<App/>);

  /**、npm run build
   * <script th:src="@{/static/js/main.js}"></script>
   * phpでもこれでいいねボタン置ける
   * 
   */
  