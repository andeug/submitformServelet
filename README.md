# submitformServelet

Submit fields from form adding emojis

In your <head> section, add the following stylesheet links. Adjust the lib/css path to match yours.
	
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
  <link href="lib/css/emoji.css" rel="stylesheet">
  
Before the end of your <body> section, add the following JavaScript links. This library depends on jQuery, so jQuery must also be included, before these scripts are run. Once again, adjust the lib/js path to match yours.
	
  <!-- ** Don't forget to Add jQuery here ** -->
  <script src="lib/js/config.js"></script>
  
  <script src="lib/js/util.js"></script>
  
  <script src="lib/js/jquery.emojiarea.js"></script>
  
  <script src="lib/js/emoji-picker.js"></script>
  
On any input field, add the data attribute data-emojiable="true".

if you need unicode inputs
Add data-emoji-input="unicode" to your input field. Only the unicode value is checked for; entering anything else has no effect.

