<template>
    <messages-list :messages="messages"/>
</template>

<script>
    import MessagesList from 'components/messages/MessageList.vue'
    export default {
        components: {
            MessagesList
        },
        data() {
            return{
                messages: frontendData.messages
            }
        }
    }
</script>

<style>

</style>

