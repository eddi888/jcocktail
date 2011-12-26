CKEDITOR.editorConfig = function(config) {
    config.extraPlugins = 'uicolor';
    config.resize_enabled = true;
    config.toolbar = 'MyToolbar';
    config.toolbar_MyToolbar = [
            [ 'Bold', 'Italic', 'Underline', 'Strike', 'Format', 'Subscript', 'Superscript', 'TextColor', 'BGColor', '-', 'Cut', 'Copy', 'Paste', 'Link', 'Unlink' ],
            [ 'Undo', 'Redo', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock', 'Source' ]
    ];
};
